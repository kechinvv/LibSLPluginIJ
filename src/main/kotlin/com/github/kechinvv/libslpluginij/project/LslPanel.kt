package com.github.kechinvv.libslpluginij.project

import com.intellij.ide.IdeBundle
import com.intellij.ide.starters.JavaStartersBundle
import com.intellij.ide.starters.shared.TextValidationFunction
import com.intellij.ide.starters.shared.ValidationFunctions
import com.intellij.ide.starters.shared.withValidation
import com.intellij.ide.util.installNameGenerators
import com.intellij.ide.util.projectWizard.ModuleBuilder
import com.intellij.ide.util.projectWizard.ModuleWizardStep
import com.intellij.ide.util.projectWizard.WizardContext
import com.intellij.ide.wizard.withVisualPadding
import com.intellij.openapi.Disposable
import com.intellij.openapi.fileChooser.FileChooserDescriptor
import com.intellij.openapi.fileChooser.FileChooserDescriptorFactory
import com.intellij.openapi.observable.properties.GraphProperty
import com.intellij.openapi.observable.properties.PropertyGraph
import com.intellij.openapi.observable.util.bindBooleanStorage
import com.intellij.openapi.observable.util.joinCanonicalPath
import com.intellij.openapi.observable.util.transform
import com.intellij.openapi.observable.util.trim
import com.intellij.openapi.ui.DialogPanel
import com.intellij.openapi.ui.TextFieldWithBrowseButton
import com.intellij.openapi.ui.getCanonicalPath
import com.intellij.openapi.ui.getPresentablePath
import com.intellij.openapi.util.io.FileUtil
import com.intellij.openapi.util.text.StringUtil
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.ui.UIBundle
import com.intellij.ui.dsl.builder.*
import org.jetbrains.annotations.Nls
import java.io.File
import javax.swing.JComponent
import javax.swing.JTextField

class LslPanel(val context: LslContext) : ModuleWizardStep() {
    protected val validatedTextComponents: MutableList<JTextField> = mutableListOf()
    private val propertyGraph: PropertyGraph = PropertyGraph()
    private val entityNameProperty: GraphProperty<String> = propertyGraph.lazyProperty(::suggestName)
    private val locationProperty: GraphProperty<String> = propertyGraph.lazyProperty(::suggestLocationByName)
    private val canonicalPathProperty = locationProperty.joinCanonicalPath(entityNameProperty)
    private val groupIdProperty: GraphProperty<String> = propertyGraph.lazyProperty { context.generatorContext.group }
    private val gitProperty: GraphProperty<Boolean> = propertyGraph.property(false)
        .bindBooleanStorage("LslProjectWizard.gitState")

    private var entityName: String by entityNameProperty.trim()
    private var location: String by locationProperty
    protected var groupId: String by groupIdProperty.trim()

    protected lateinit var groupRow: Row


    private val parentDisposable: Disposable = context.parentDisposable;
    private val moduleBuilder: ModuleBuilder = context.moduleBuilder;
    private val wizardContext: WizardContext = context.wizardContext;
    private val generatorContext: LslGeneratorContext = context.generatorContext;


    private val contentPanel: DialogPanel by lazy { createComponent() }

    override fun getComponent(): JComponent {
        return contentPanel
    }

    override fun updateDataModel() {
        generatorContext.gitIntegration = gitProperty.get()
        generatorContext.group = groupId

        wizardContext.projectName = entityName
        wizardContext.setProjectFileDirectory(FileUtil.join(location, entityName))
    }


    private fun createComponent(): DialogPanel {

        return panel {
            addProjectLocationUi()

        }.withVisualPadding(topField = true)
    }

    private fun Panel.addProjectLocationUi() {
        row(UIBundle.message("label.project.wizard.new.project.name")) {
            textField()
                .bindText(entityNameProperty)
                .withSpecialValidation(
                    listOf(ValidationFunctions.CHECK_NOT_EMPTY, ValidationFunctions.CHECK_SIMPLE_NAME_FORMAT),
                    ValidationFunctions.createLocationWarningValidator(locationProperty)
                )
                .columns(COLUMNS_MEDIUM)
                .gap(RightGap.SMALL)
                .focused()

            installNameGenerators(moduleBuilder.builderId, entityNameProperty)
        }.bottomGap(BottomGap.SMALL)

        val locationRow = row(UIBundle.message("label.project.wizard.new.project.location")) {
            val commentLabel = projectLocationField(locationProperty, wizardContext)
                .align(AlignX.FILL)
                .withSpecialValidation(
                    ValidationFunctions.CHECK_NOT_EMPTY,
                    ValidationFunctions.CHECK_LOCATION_FOR_ERROR
                )
                .comment(getLocationComment(), 100)
                .comment!!

            entityNameProperty.afterChange { commentLabel.text = getLocationComment() }
            locationProperty.afterChange { commentLabel.text = getLocationComment() }
        }

        if (wizardContext.isCreatingNewProject) {
            // Git should not be enabled for single module
            row("") {
                checkBox(UIBundle.message("label.project.wizard.new.project.git.checkbox"))
                    .bindSelected(gitProperty)
            }.bottomGap(BottomGap.SMALL)
        } else {
            locationRow.bottomGap(BottomGap.SMALL)
        }

        row(JavaStartersBundle.message("title.project.group.label")) {
            groupRow = this

            textField()
                .bindText(groupIdProperty)
                .columns(COLUMNS_MEDIUM)
                .withSpecialValidation(
                    ValidationFunctions.CHECK_NO_WHITESPACES,
                    ValidationFunctions.CHECK_GROUP_FORMAT,
                    ValidationFunctions.CHECK_NO_RESERVED_WORDS)
        }.bottomGap(BottomGap.SMALL)
    }

    private fun suggestName(): String {
        return suggestName(context.artifact)
    }

    private fun suggestName(prefix: String): String {
        val projectFileDirectory = File(wizardContext.projectFileDirectory)
        return FileUtil.createSequentFileName(projectFileDirectory, prefix, "")
    }

    private fun suggestLocationByName(): String {
        return wizardContext.projectFileDirectory
    }

    @Suppress("SameParameterValue")
    private fun <T : JComponent> Cell<T>.withSpecialValidation(vararg errorValidationUnits: TextValidationFunction): Cell<T> =
        withValidation(this, errorValidationUnits.asList(), null, validatedTextComponents, parentDisposable)

    private fun <T : JComponent> Cell<T>.withSpecialValidation(
        errorValidationUnits: List<TextValidationFunction>,
        warningValidationUnit: TextValidationFunction?
    ): Cell<T> {
        return withValidation(
            this,
            errorValidationUnits,
            warningValidationUnit,
            validatedTextComponents,
            parentDisposable
        )
    }

    private fun Row.projectLocationField(
        locationProperty: GraphProperty<String>,
        wizardContext: WizardContext
    ): Cell<TextFieldWithBrowseButton> {
        //TODO: what happened with file choose descriptor?
        val title = IdeBundle.message("title.select.project.file.directory", wizardContext.presentationName)
        val fileChooserDescriptor =
            FileChooserDescriptorFactory.createSingleLocalFileDescriptor().withFileFilter { it.isDirectory }
        val fileChosen = { file: VirtualFile -> getPresentablePath(file.path) }
        val property = locationProperty.transform(::getPresentablePath, ::getCanonicalPath)
        return textFieldWithBrowseButton(title, wizardContext.project, fileChooserDescriptor,  fileChosen)
            .bindText(property)
    }

    private fun getLocationComment(): @Nls String {
        val shortPath = StringUtil.shortenPathWithEllipsis(getPresentablePath(canonicalPathProperty.get()), 60)
        return UIBundle.message(
            "label.project.wizard.new.project.path.description",
            wizardContext.isCreatingNewProjectInt,
            shortPath
        )
    }


}