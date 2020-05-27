package org.test

public class PlayStoreDistribution implements Distribution {

    def pipeline

    PlayStoreDistribution(pipeline){
        this.pipeline = pipeline
    }

    def upload(String aabPath) {
        def packageName = pipeline.sh(script: "java -jar ~/bundletool.jar dump manifest --bundle $aabPath --xpath /manifest/@package", returnStdout: true)

        pipeline.sh 'bundle install'
        pipeline.sh "bundle exec fastlane supply --aab $aabPath --skip_upload_apk true --track internal --package_name $packageName"
    }
}
