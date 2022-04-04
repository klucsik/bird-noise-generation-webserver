# Version 1.0.9
* FEATURE: Basic authentication
* FEATURE: Show Error counts for last 3 days, and show error logs.
# Version 1.0.8
* FEATURE: Documentation available in server. Added user manual.
* TECHNICAL FEATURE: Cleaned up unused code
* TECHNICAL FEATURE: circuit breaker for fresh version checker (disturbances in webupdate server will not affect device page load)
# version 1.0.7
* FEATURE: Send playparams to device converted to UTC
* TECHNICAL FEATURE: Secrets to Jenkins Credentials
* TECHNICAL FEATURE: version tag on docker images. Displayed version updated from version file in buildtime.
* HOTFIX: Various problems with device version
# Version 1.0.6
* FEATURE: Show device versions, and outdated device number
# Version 1.0.5
* TECHNICAL FEATURE: update dependencies
* TECHNICAL FEATURE: set servers timezone to Budapest
# Version 1.0.4
* FEATURE: reworked logging for saving bits in device
# Version 1.0.3
* BUG FIX: device playparam list for devices have shown all dpps, now it filter it correctly.
* BUG FIX: misleading tracklist display in playparam edit dropdown lists
# Version 1.0.2
* FEATURE:playparam FE, menu FE upgrade
* TECHNICAL FEATURE: multiarch build
* TECHNICAL FEATURE: jenkins k8s agent
# Version 1.0.1
* FEATURE: Facelift
* TECHNICAL FEATURE: Added postgreSQL database for k8s deployments
* FEATURE: Added logging to devices
* TECHNICAL_FEATURE: moved build agent into the k8s cluster

# Version 1.0.0
* FEATURE: Added Frontend
* FEATURE: Track length for devices
* FEATURE: Device updating its playparams BE part
* TECHNICAL_FEATURE: registry got a hostname on registry.klucsik.fun and changes for new infra
* FEATURE: Created device voltage BE part, and report voltage endpoint for devices
* FEATURE: Add validation for playUnits
* TECHNICAL_FEATURE: Run api tests in jenkins
* TECHNICAL FEATURE: Added trimmed and Unique custom validators
* FEATURE: Added validation for tracks
* TECHNICAL FEATURE: Added validation dependencies
* FEATURE: Device BE
* TECHNICAL FEATURE: Added swagger ui for api documentation
* FEATURE: PlayParams BE
* FEATURE: PlayUnit BE
* FEATURE: Tracks BE 
* TECHNICAL FEATURE: Reorganize jenkins steps. Skip build step if codebase didn't changed.
* TECHNICAL FEATURE: Move frontend repo inside the backend repo and create the common deployment.
The backend is routed to `{baseurl}/api`, and the frontend is routed to `{baseurl}/`.