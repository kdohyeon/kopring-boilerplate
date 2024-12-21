rootProject.name = "kopring"

include("boilerplate-adapters:adapter-http")
include("boilerplate-adapters:adapter-persistence")
include("boilerplate-adapters:adapter-redis")

include("boilerplate-apps:app-api")
include("boilerplate-apps:app-batch")

include("boilerplate-commons")

include("boilerplate-core:core-domain")
include("boilerplate-core:core-port")
include("boilerplate-core:core-service")
include("boilerplate-core:core-usecase")