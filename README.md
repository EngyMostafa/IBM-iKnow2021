# IBM-iKnow2021
This asset is used for logging in appconnect using kafka. The message to be logged is sent to kafka producer which is then consumed by kafka consumer flow.
The kafka consumer flow do the work of logging to COS & logDNA.
kafka consumer is not logging only in logDNA as logDNA has limits for the message size and may truncate the message, so we sve it in a json file in COS and just log the COS url to logDNA.

Built With

    appconnect v10.0.0.18
    ESQL
    Java

Prerequisites

	appconnect v10.0.0.18 or higher
	jdk 1.7
