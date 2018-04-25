
## Instructions

* NOTE: This setup has been based on [this tutorial](https://www.justdave.net/dave/2015/05/04/how-to-write-a-minecraftbukkit-plugin-for-spigot-1-8/)

1. Install + Prepare Eclipse
    1. NOTE: This project has been built with Eclipse Oxygen.
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
1. Prepare 🚰 Spigot `BuildTools`
    1. Download `BuildTools.jar` from [https://hub.spigotmc.org/jenkins/job/BuildTools/](https://hub.spigotmc.org/jenkins/job/BuildTools/)
    1. Copy `BuildTools.jar` into the `buldtools` folder (within this folder)
    1. Build Spiggot BuildTools
        * In Terminal, `cd` to the `buldtools` folder
        * NOTE: You need to build the version that matches your Server!
        * Run: `java -jar BuildTools.jar --rev <your minecraft version>`
            * For example: `java -jar BuildTools.jar --rev 1.12.2`
    1. For more information, go to: [https://www.spigotmc.org/wiki/buildtools/](https://www.spigotmc.org/wiki/buildtools/)
1. **[Ignore this (this has already been taken care of)]** Project setup:
    1. Create Project in `Eclipse` (e.g. `MyTestPlugin`)
        * Choose new folder (e.g. `MyTestPlugin`)
    1. Create Package (e.g. `mc.mytestplugin`)
    1. Add new source folder (e.g. `mc/MyTestPlugin/src`) 
    1. Add `Main` class to `src` folder
    1. Add `plugin.yml` file
    1. Add `build.xml` file
        * The `<jar>` -> `<fileset>` -> `dir` property must be same as the project output path (`bin`)
        * NOTE: The project output path is configured in: `Project Properties` -> `Source` -> (at the bottom) `Default output folder`
1. Add `Make JAR` builder
    * NOTE: The `Make JAR` builder allows us to automatically create our `*.jar` file and export it into our Minecraft server's `plugin` folder.
    * Open `Builders` -> Add `Make JAR` ant script
        * NOTE: This might already exist, in which case, you are done!
    * Buildfile: `${project_loc}/build.xml`
    * Arguments: `-Dtargetdir=${targetdir}`
    * Done!
1. Add 🚰 Spigot to your `classpath`:
    * If you see a Spigot library that cannot be found, or has the wrong version, in that list, remove it
    * right-click your project -> `Properties` -> `Java Build Path` -> `Libraries` -> `Add external jar`
    * Go to path: `buildtools/Spigot/Spigot-API/target`
    * Select the `SHADED` jar (e.g. `spigot-api-1.12-R0.1-SNAPSHOT-shaded.jar`) -> `Open`
    *  -> `Apply and close`
    * Add `Source attachment` to your 🚰 Spigot classpath
        * `Java Build Path` -> `Libraries` -> Click the small "+" next to 🚰 `Spigot` -> Source attachment -> Edit
        * Choose `External folder` and select `../buildtools/Spigot/Spigot-API/src/main/java`
        * <img src="https://i.imgur.com/20eyNVc.png" width="600px" />
1. Update `targetdir` variable
    * Open your variables: `Eclipse` -> `Preferences` (`Command ,` on MAC) -> `Run/Debug` -> `String Substitution`
    * Add/edit the `targetdir` variable to match your servers' `plugins` directory
    * <img src="https://i.imgur.com/7QK2Erd.png" width="400px"/>
    * In case of problems: [see screenshots + more explanation here](https://stackoverflow.com/questions/4660366/how-to-set-ant-properties-based-on-variables-in-eclipse/4989916)
1. Go!
    * NOTE: Every time you make changes, you need to restart the server.


## References

1. [YouTube: Pogo - Coding and More](https://www.youtube.com/watch?v=bVySbfryiMM&list=PLAF3anQEEkzREsHA8yZzVhc3_GHcPnqxR)
    * 80+ coding videos, starting from the basics and goes into tons of advanced features
1. [More recent tutorial on setting up your first plugin](https://www.justdave.net/dave/2015/05/04/how-to-write-a-minecraftbukkit-plugin-for-spigot-1-8/)
