
## Instructions

* NOTE: This setup has been based on [this tutorial](https://www.justdave.net/dave/2015/05/04/how-to-write-a-minecraftbukkit-plugin-for-spigot-1-8/)

1. Install + Prepare Eclipse
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
1. Prepare 🚰 Spiggot `BuildTools`
    1. Download `BuildTools.jar` from [https://hub.spigotmc.org/jenkins/job/BuildTools/](https://hub.spigotmc.org/jenkins/job/BuildTools/)
    1. Copy `BuildTools.jar` into the `buldtools` folder (within this folder)
    1. Build Spiggot BuildTools
        * In Terminal, go to the `buldtools` folder
        * `java -jar BuildTools.jar` # build BuildTools
1. **[Ignore this (this has already been taken care of)]** Project setup:
    1. Create Project in `Eclipse` (e.g. `MyTestPlugin`)
        * Choose new folder (e.g. `mc/MyTestPlugin`)!!!
    1. Create Package (e.g. `mc.MyTestPlugin`)
    1. Add new source folder (e.g. `mc/MyTestPlugin/src`) 
    1. Add `Main` class to `src` folder
    1. Add `plugin.yml` file
    1. Add `build.xml` file
        * NOTE: We can use the `build.xml` file to automatically add build steps. By default, we use it to automatically create our `*.jar` file and export it into our Minecraft server's `plugin` folder.
        * The `<jar>` -> `<fileset>` -> `dir` property must be same as the project output path (`bin`)
        * NOTE: The project output path is configured in: `Project Properties` -> `Source` -> (at the bottom) `Default output folder`
    1. Open `Builders` -> Add `Make JAR` ant script
      * at the top, choose `build.xml` and done!
1. Open `build.xml`, change the `destfile` to your minecraft server's `plugins` folder!
1. Go!
    * NOTE: Every time you make changes, you need to restart the server.


## References

1. [YouTube: Pogo - Coding and More](https://www.youtube.com/watch?v=bVySbfryiMM&list=PLAF3anQEEkzREsHA8yZzVhc3_GHcPnqxR)
    * 80+ coding videos, starting from the basics and goes into tons of advanced features
1. [More recent tutorial on setting up your first plugin](https://www.justdave.net/dave/2015/05/04/how-to-write-a-minecraftbukkit-plugin-for-spigot-1-8/)