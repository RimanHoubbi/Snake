# Purpose:

The purpose of this repo is to test and evaluate the embedded feature annotations plugin
[HAnS](https://bitbucket.org/easelab/hans-text/src) for IntelliJ.

### Embedded Feature Annotations

The usage of feature annotations is to map sections of code to functionality of the software. The system of annotations
that this plugin uses is able to map features to any file type and programming language (except languages that do not
have support for comments). The central part of this annotation system is a file with the extension ".feature-model".
This is a feature hierarchy model, describing feature names, and their hierarchy in textual form. These features may
then be references by mapping them to code.

#### Feature Reference Names

Inside the feature hierarchy model, features with the same feature name may appear twice or more often. To reference
features uniquely the individual feature is pre-extended by its ancestor until the combined feature reference is unique
(separated by "::"). This technique is called Least-Partially-Qualified name, short LPQ.

```folderannotation
Feature_A::CommonFeatureName
Feature_B::CommonFeatureName
```

#### Feature-to-folder mapping

The purpose of this file is to map complete folders and their content to one or more feature references. The mapping of
feature references to folders allows linking specific features to the folder, including all its sub-folders and files.
With this, the mapping of complete folder structures to features is possible and may substitute the feature-to-file
mapping. The mapping file is located on the top level inside the to be annotated folder.

```folderannotation
Feature_A
Feature_B::Feature_C
```

#### Feature-to-file mapping

The feature-to-file mapping is a specialized file to map one or more file(s) and its/ their content to one or more
feature references. All content of the linked file is considered fully to be part of the given feature references. The
mapping file must be stored in the same folder as the source code files and covers only the file in this folder.

```fileannotation
FileNameA, FileNameB
FeatureA, Feature_B::Feature_C
```

#### Feature-to-code mapping

The feature-to-code mapping serves to link specific blocks and lines of code to one or more features. The parts of the
source code which are mapped to a certain feature are called annotation scopes. An annotation scope is surrounded by
annotation markers and contains at least one feature reference. 

```java
// &begin[Feature_A]
private void example(){
        getAnotherExample(); // &line[Feature_B]
        }
// &end[Feature_A]
```

### The Snake

A simple snake game in java, forked from [@hexadeciman](https://github.com/hexadeciman/Snake). Using Threads and Java
Swing to display the game. The code is well commented, if you have any questions or want to continue this project feel
free to do so 👌

### How it looks:

![alt tag](https://i.imgur.com/RVxiGad.png)

#### Requirements:

* IntelliJ installed
* Recommended JDK 14
* Installed HAnS-text plugin

#### Installation of the HAnS plugin:

* Open Plugins in Settings/Preferences. Click the settings icon and select "Install Plugin from Disk..."
  ![alt tag](./guide/guide.png)
* Choose the path to the zip file of the plugin.

### Warmup task

0. Add a file with the extension `.feature-to-folder` to the *graphics* package.
    * Verify that the feature `Playing_Area` is defined in the Feature Model via the Feature View tab (bottom left).
    * Add the feature `Playing_Area` to the file.

You have now mapped the feature *Playing_Area* to the *graphics* directory.

### Tasks:

1. Implement and annotate a feature that adds a red poison tile that if eaten shrinks the snake by three tiles. If the
  snake is less than or equal to three in length, the snake dies. *Hint: The poison would follow similar implementation
  as the feature `Food`.*
2. Add a file with the extension `.feature-to-file` to the *pojo* package.
    * Verify that the feature `Tile` is defined in the Feature Model.
    * Map the feature `Tile` to the file `Tuple.java`.
3. Rename the "Head" feature to "Positions".
4. Inspect the .feature-model and .feature-to-file files.
5. After the above tasks are completed, answer the questions [here](https://forms.gle/xWZdGAT9wo6xygov7). 
