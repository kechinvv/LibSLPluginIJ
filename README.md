# LibSL Support Plugin

![Build](https://github.com/kechinvv/LibSLPluginIJ/workflows/Build/badge.svg)
[![Version](https://img.shields.io/jetbrains/plugin/v/23222-libsl-support.svg)](https://plugins.jetbrains.com/plugin/23222-libsl-support)
[![Downloads](https://img.shields.io/jetbrains/plugin/d/23222-libsl-support.svg)](https://plugins.jetbrains.com/plugin/23222-libsl-support)

## ToDo list

### Basic features

- [x] Basic highlighter
- [ ] Find Usages
    - [x] functions and procs
    - [x] types and automatons
    - [x] automaton scope variables
    - [x] actions
    - [ ] local and global variables
    - [x] annotations
- [ ] Completion
- [ ] Formatter
    - [x] tabs
    - [x] lines between statements in automaton
    - [ ] spaces around tokens
- [ ] Helper
    - [ ] Name scope resolving
    - [ ] Tips about constructions
- [x] Project generator
- [x] File templates
- [x] Custom tools support

### Unique features

- [ ] Taint configs generation
- [ ] Templates generation
- [ ] Mining specification

### Dev

- [x] CI/CD
- [ ] Refactoring
- [ ] Null safety
- [ ] Optimization of search algorithms for find usages
- [ ] Tests
  - [x] [Test cases description (note for unit tests)](https://docs.google.com/document/d/1lOTSDMsUHc6SwaIhIhQRKjiW5FJ6AfxI3b3VRvDrc6Y/edit?usp=sharing)
  - [x] [Test cases for UI manual tests](https://docs.google.com/document/d/1kTs9C1gYA5PSXz7a1tbcuGEE8CMNjGvvdltq6LA8SX8/edit?usp=sharing)
  - [ ] Unit tests

# Description
<!-- Plugin description -->
This plugin is
for [LibSL](https://www.researchgate.net/publication/325074393_LibSL_Language_for_Specification_of_Software_Libraries)
supporting. It is very easy to use:

- Install the plugin
- Add your custom tools for project via "Configure LibSL tools..."
- Enjoy LibSL support: highlighting, findUsages and other features

More features in future releases.
<!-- Plugin description end -->

## Installation

- Using the IDE built-in plugin system:

  <kbd>Settings/Preferences</kbd> > <kbd>Plugins</kbd> > <kbd>Marketplace</kbd> > <kbd>Search for "LibSL
  Support"</kbd> >
  <kbd>Install</kbd>

- Manually:

  Download the [latest release](https://github.com/kechinvv/LibSLPluginIJ/releases/latest) and install it manually using
  <kbd>Settings/Preferences</kbd> > <kbd>Plugins</kbd> > <kbd>⚙️</kbd> > <kbd>Install plugin from disk...</kbd>

---
Plugin based on the [IntelliJ Platform Plugin Template][template].

[template]: https://github.com/JetBrains/intellij-platform-plugin-template

[docs:plugin-description]: https://plugins.jetbrains.com/docs/intellij/plugin-user-experience.html#plugin-description-and-presentation
