# the issue with exists-maven-plugin
run
`docker run -d -p 8081:8081 --name nexus -v  /Users/clement2/code/tutorials/dockervol/nexus sonatype/nexus3;`
(you can modify of course the volume path to whatever folder you prefer)

run
`docker ps`
get the containerId

run (of course replace the containerId by what you found previously)
`docker exec -it containerId sh;`

from within the docker container 
run `cat /nexus-data/admin.password`

copy the password and got to `http://localhost:8081`

sign in using `admin`/thePasswordYouJustCopied

once signed in 
through the wizard just created `admin`/`admin`  (as user/pwd)

--> you are now good to go.

`mvn clean deploy`
--> it FAILS with ` Cannot invoke "String.length()" because "path" is null`

remove the `exists-maven-plugin` from the `pom.xml` file
run 
`mvn clean deploy`
--> it WORKS

I believe The Failure is due to the fact that the plugin is looking for a `<distributionManagement>` section but this is not necessary to have it
because this can actually be set in the maven settings.xml file as altReleaseDeploymentRepository.