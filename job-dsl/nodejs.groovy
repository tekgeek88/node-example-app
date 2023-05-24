job('NodeJS example') {
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
        nodejs('nodejs') // this is the name of the NodeJS installation in 
                         // Manage Jenkins -> Configure Tools -> NodeJS Installations -> Name
    }
    steps {
        shell("npm install")
    }
}