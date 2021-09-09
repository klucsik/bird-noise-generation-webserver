* TECHNICAL FEATURE: Added postgreSQL database for k8s deployments
* FEATURE: Added logging to devices

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