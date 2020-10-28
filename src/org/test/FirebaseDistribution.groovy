package org.test

public class FirebaseDistribution implements Distribution {

    def pipeline
    def appId
    def token
    def groups
    def releaseNotes

    FirebaseDistribution(pipeline, appId, token, groups, releaseNotes) {
        this.pipeline = pipeline
        this.appId = appId
        this.token = token
        this.groups = groups
        this.releaseNotes = releaseNotes
    }

    def upload(String aabPath) {
        pipeline.sh "firebase appdistribution:distribute $aabPath --app $appId --token $token --release-notes \"$releaseNotes\" --groups \"$groups\")"
    }
}
