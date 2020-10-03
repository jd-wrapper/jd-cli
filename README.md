# JD-CLI

JD-CLI, a standalone command line utility that displays Java sources from CLASS files.

- Java Decompiler projects home page: [http://java-decompiler.github.io](http://java-decompiler.github.io)
- JD-GUI source code: [https://github.com/java-decompiler/jd-gui](https://github.com/java-decompiler/jd-gui)
- JD-CLI source code: [https://github.com/jd-wrapper/jd-cli](https://github.com/jd-wrapper/jd-cli)

## Description
JD-CLI is a standalone command line utility that displays Java source codes of
".class" files. You can browse the reconstructed source code with the JD-CLI
for instant access to methods and fields.

## How to build JD-CLI ?
```
> git clone https://github.com/java-decompiler/jd-gui.git
> cd jd-cli
> ./gradlew build
```
generate :
- _"build/libs/jd-cli-x.y.z.jar"_
- _"build/libs/jd-cli-x.y.z-min.jar"_
- _"build/distributions/jd-cli-windows-x.y.z.zip"_
- _"build/distributions/jd-cli-osx-x.y.z.tar"_
- _"build/distributions/jd-cli-x.y.z.deb"_
- _"build/distributions/jd-cli-x.y.z.rpm"_

## How to launch JD-CLI ?
- Double-click on _"jd-cli-x.y.z.jar"_
- Double-click on _"jd-cli.exe"_ application from Windows
- Double-click on _"JD-CLI"_ application from Mac OSX
- Execute _"java -jar jd-cli-x.y.z.jar"_ or _"java -classpath jd-cli-x.y.z.jar org.jd.cli.App"_

## How to use JD-CLI ?
```
> jd-cli.exe -h
example:
jd-cli(.exe) Example.class
jd-cli(.exe) -b example.jar org.aaa.Example.class
jd-cli(.exe) -b org\aaa Example.class

usage:
 classes...      : Specify the class files...
 -b (--base) VAL : Specify the reference directory or Jar file.
 -h (--help)     : Show helps. (default: false)
 -l (--loc)      : Show full classpath. (default: false)
 -m (--meta)     : Show metadatas.(e.g. Filename, Java versions..) (default:false)
 -n (--line)     : Show Line Number. (default: false)
 -r (--real)     : Realignment Line Number. (default: false)
 -u (--unicode)  : Escapes Unicode Charset. (default: false)
```

## How to extend JD-CLI ?
```
> ./gradlew idea
```
generate Idea Intellij project
```
> ./gradlew eclipse
```
generate Eclipse project
```
> java -classpath jd-cli-x.y.z.jar;myextension1.jar;myextension2.jar org.jd.cli.App
```
launch JD-CLI with your extensions

## How to uninstall JD-CLI ?
- Java: Delete "jd-cli-x.y.z.jar" and "jd-cli.cfg".
- Mac OSX: Drag and drop "JD-CLI" application into the trash.
- Windows: Delete "jd-cli.exe" and "jd-cli.cfg".

## License
Released under the [GNU GPL v3](LICENSE).
