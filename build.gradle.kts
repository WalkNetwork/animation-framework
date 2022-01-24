plugins {
	kotlin("jvm") version "1.6.10"
	kotlin("plugin.serialization") version "1.6.10"
	id("java")
	id("com.github.johnrengelman.shadow") version "7.0.0"
	id("maven-publish")
	id("io.github.gradle-nexus.publish-plugin") version "1.1.0"
	id("signing")
}

group = "io.github.uinnn"
version = "1.0.0"

repositories {
	mavenLocal()
	mavenCentral()
}

dependencies {
	compileOnly("io.github.uinnn:walk-server:2.3.40")
	compileOnly(kotlin("stdlib-jdk8"))
}

nexusPublishing {
	repositories {
		sonatype {
			nexusUrl.set(uri("https://s01.oss.sonatype.org/service/local/"))
			snapshotRepositoryUrl.set(uri("https://s01.oss.sonatype.org/content/repositories/snapshots/"))
		}
	}
}

tasks {
	publishing {
		repositories {
			maven {
				url = uri("https://repo.maven.apache.org/maven2/")
			}
		}
		
		publications {
			create<MavenPublication>("maven") {
				from(project.components["kotlin"])
				
				val sourcesJar by creating(Jar::class) {
					archiveClassifier.set("sources")
					from(sourceSets.main.get().allSource)
				}
				
				val javadocJar by creating(Jar::class) {
					dependsOn.add(javadoc)
					archiveClassifier.set("javadoc")
					from(javadoc)
				}
				
				setArtifacts(listOf(sourcesJar, javadocJar, jar))
				
				groupId = "io.github.uinnn"
				artifactId = "animation-framework"
				version = project.version.toString()
				
				pom {
					name.set("game-framework")
					description.set("a animation framework for WalkMC Network written in kotlin")
					url.set("https://github.com/uinnn/game-framework")
					developers {
						developer {
							id.set("uinnn")
							name.set("Uin Carrara")
							email.set("uin.carrara@gmail.com")
						}
					}
					licenses {
						license {
							name.set("MIT Licenses")
						}
					}
					scm {
						url.set("https://github.com/uinnn/animation-framework/tree/master/src")
					}
				}
			}
		}
	}
	
	signing {
		sign(publishing.publications["maven"])
	}
	
	compileKotlin {
		targetCompatibility = "1.8"
		sourceCompatibility = "1.8"
		
		kotlinOptions.freeCompilerArgs +=
			"-Xopt-in=kotlin.time.ExperimentalTime," +
				"kotlin.ExperimentalStdlibApi," +
				"kotlinx.coroutines.DelicateCoroutinesApi," +
				"kotlinx.coroutines.ExperimentalCoroutinesApi," +
				"kotlinx.coroutines.InternalCoroutinesApi"
	}
	
	shadowJar {
		destinationDir = file("C:\\Users\\Cliente\\Jetbrains\\Servidores\\Local\\plugins")
		archiveName = "${project.name}.jar"
		baseName = project.name
		version = project.version.toString()
		relocate("net.minecraft.server", "net.minecraft.server.v1_8_R3")
	}
}
