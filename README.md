`JImagePanel` is a Java Swing component designed to display a [BufferedImage](https://docs.oracle.com/en/java/javase/24/docs/api/java.desktop/java/awt/image/BufferedImage.html)
and to automatically resize the image to fit within the panel's bounds.

## Table of Contents

* [Installation](https://github.com/Valkryst/JImagePanel#installation)
    * [Gradle](https://github.com/Valkryst/JImagePanel#-gradle)
    * [Maven](https://github.com/Valkryst/JImagePanel#-maven)
    * [sbt](https://github.com/Valkryst/JImagePanel#-scala-sbt)

## Installation

JImagePanel is hosted on the [JitPack package repository](https://jitpack.io/#Valkryst/JImagePanel)
which supports Gradle, Maven, and sbt.

### ![Gradle](https://i.imgur.com/qtc6bXq.png?1) Gradle

Add JitPack to your `build.gradle` at the end of repositories.

```
allprojects {
	repositories {
		...
		maven { url 'https://jitpack.io' }
	}
}
```

Add JImagePanel as a dependency.

```
dependencies {
	implementation 'com.github.Valkryst:JImagePanel:2025.5.23'
}
```

### ![Maven](https://i.imgur.com/2TZzobp.png?1) Maven

Add JitPack as a repository.

``` xml
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>
```
Add JImagePanel as a dependency.

```xml
<dependency>
    <groupId>com.github.Valkryst</groupId>
    <artifactId>JImagePanel</artifactId>
    <version>2025.5.23</version>
</dependency>
```

### ![Scala SBT](https://i.imgur.com/Nqv3mVd.png?1) Scala SBT

Add JitPack as a resolver.

```
resolvers += "jitpack" at "https://jitpack.io"
```

Add JImagePanel as a dependency.

```
libraryDependencies += "com.github.Valkryst" % "JImagePanel" % "2025.5.23"
```