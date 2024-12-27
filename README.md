# Server Ping
Server Ping is a server-side mod that allows spoofing the player list and count displayed on the server screen.

## Installation
1. Download and install [Fabric Loader](https://fabricmc.net/use/) if you haven't already.
2. Get the latest release from Actions under Artifacts.
3. Put it in your mod folder.

## Configuration
The config is located at `config/serverping.json`

```json
{
  "playerCount": {
    "enabled": true,
    "maxCount": 20,
    "count": 2
  },
  "playerList": {
    "enabled": true,
    "names": [
      "Ghost",
      "Shepherd"
    ]
  }
}
```
