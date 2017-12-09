
## Instructions

Based on: 
* [https://www.justdave.net/dave/2015/05/04/how-to-write-a-minecraftbukkit-plugin-for-spigot-1-8/](https://www.justdave.net/dave/2015/05/04/how-to-write-a-minecraftbukkit-plugin-for-spigot-1-8/)

1. Install Java + Eclipse
  * For MAC:
  	1. Open Terminal
  	1. `ruby -e "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/master/install)"` # install homebrew
  	1. `brew cask install java` # install Java
  	1. `brew cask install eclipse-java` # install Eclipse
1. Create new folder for your Minecraft code (e.g. `mc`)
1. Download `BuildTools.jar` from [https://hub.spigotmc.org/jenkins/job/BuildTools/](https://hub.spigotmc.org/jenkins/job/BuildTools/) and copy to `mc` folder
1. Build Spiggot BuildTools
  * In Terminal, go to `mc` folder
  * `java -jar BuildTools.jar` # build BuildTools
1. Install `Eclipse` -> `YEdit Plugin`
1. Create Project in `Eclipse` (e.g. `MyFirstMinecraftPlugin`)
  * Choose new folder (e.g. `mc/MyFirstMinecraftPlugin`)!!!
1. Create Package (e.g. `mc.myfirstminecraftplugin`)
1. [EXTRA STEP] Add new source folder (e.g. `mc/MyFirstMinecraftPlugin/src`) 
1. Add `Main` class to `src` folder
1. Add `plugin.yml` file
1. Add `build.xml` file
  * build.xml's `<jar>` -> `<fileset>` -> `dir` must be same as output path (`bin`)
    * `Project Properties` -> `Source` -> At the bottom `Default output folder`
1. Add `Make JAR` Build Script
1. Go!


## TODO
* Screenshots + video