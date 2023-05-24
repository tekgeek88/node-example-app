job('NodeJS Docker example') {
    label('Agent1-Ubuntu')
    scm {
        git('https://github.com/tekgeek88/node-example-app.git') {  node -> // is hudson.plugins.git.GitSCM
            node / gitConfigName('DSL User')
            node / gitConfigEmail('carl.argabright@gmail.com')
        }
    }
    triggers {
        scm('H/2 * * * *')
    }
    wrappers {
        nodejs('NodeJS') // this is the name of the NodeJS installation in 
                         // Manage Jenkins -> Configure Tools -> NodeJS Installations -> Name
    }
    steps {
        dockerBuildAndPublish {
            repositoryName('tekgeek88/node-example-app')
            tag('${GIT_REVISION,length=9}')
            registryCredentials('docker-hub')
            forcePull(false)
            forceTag(true)
            createFingerprints(false)
            skipDecorate()
        }
    }
}