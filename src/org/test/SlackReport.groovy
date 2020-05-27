package org.test

class SlackReport implements Report {

    def pipeline

    private String token
    private String slackChannel
    private String domain


    SlackReport(pipeline, String token, String slackDomain, String slackChannel) {
        this.pipeline = pipeline
        this.token = token
        this.slackChannel = slackChannel
        this.domain = slackDomain
    }

    def getUserByEmail(String email) {
        def author = pipeline.sh(
                script: 'curl https://slack.com/api/users.lookupByEmail\\?token\\=' + token +
                        '\\&email\\=' + email + ' | sed -n \'s|.*"id":"\\([^"]*\\)".*|\\1|p\'',
                returnStdout: true
        )

        return author
    }

    def sendMessage(String title, String message, String color) {
        def response = pipeline.slackSend(channel: "${slackChannel}",
                teamDomain: "${domain}",
                token: "${token}",
                botUser: true,
                message: "${title}",
                attachments: [
                        [
                                color: "${color}",
                                text : "${message}"
                        ]
                ])
        println "Response: ${response}"
    }
}

