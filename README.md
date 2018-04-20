
## Instructions

Based on: 
* [https://www.justdave.net/dave/2015/05/04/how-to-write-a-minecraftbukkit-plugin-for-spigot-1-8/](https://www.justdave.net/dave/2015/05/04/how-to-write-a-minecraftbukkit-plugin-for-spigot-1-8/)

1. Install
  1. Install Eclipse
    * On MAC:
      1. Open Terminal
      1. install homebrew: `ruby -e "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/master/install)"`
      1. `brew cask install java` # install Java
      1. `brew cask install eclipse-java` # install Eclipse
    * On Windows: Download from official website
  1. Open `Eclipse`
  1. Install `YEdit`:
    * Open `Help` -> `Eclipse Marketplace`
    * Look for and install `YEdit Plugin`
1. Download `BuildTools.jar` from [https://hub.spigotmc.org/jenkins/job/BuildTools/](https://hub.spigotmc.org/jenkins/job/BuildTools/) and copy to this folder
1. Build Spiggot BuildTools
  * In Terminal, go to this folder
  * `java -jar BuildTools.jar` # build BuildTools
1. (Ignore this) These steps are already taken care of:
  1. Create Project in `Eclipse` (e.g. `MyFirstPlugin`)
    * Choose new folder (e.g. `mc/MyFirstPlugin`)!!!
  1. Create Package (e.g. `mc.MyFirstPlugin`)
  1. Add new source folder (e.g. `mc/MyFirstPlugin/src`) 
  1. Add `Main` class to `src` folder
  1. Add `plugin.yml` file
  1. Add `build.xml` file
    * build.xml's `<jar>` -> `<fileset>` -> `dir` must be same as output path (`bin`)
      * `Project Properties` -> `Source` -> At the bottom `Default output folder`
  1. Add `Make JAR` Build Script
1. Open `build.xml`, change the `destfile` to your minecraft server's `plugins` folder!
1. Go!


## TODO
* Screenshots + video