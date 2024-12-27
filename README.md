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
    "spoofingType": "REPLACE",
    "names": [
      "Ghost",
      "Shepherd"
    ]
  }
}
```

### Player Count (`playerCount`)
This controls the number that appears when the server is pinged.
- `enabled`: Whether or not to spoof the number based on the configuration.
- `maxCount`: The spoofed max player count of the server (2/**20**).
- `count`: The spoofed online player count of the server (**2**/20).

### Player List (`playerList`)
This controls the list that appears when you hover your mouse over the player count in the server list.
- `spoofingType`: The player list spoofing type. This has several values:
  - `DISABLED`: Disables the spoofing completely.
  - `ANONYMIZE`: Forcibly anonymizes player names into "Anonymous Player", similar to the "Allow Server Listings: OFF" option in game.
  - `REPLACE`: Replaces the player list completely with `names`.
- `names`: The fake list of player names that you want to return. This setting is ignored when `spoofingType` is not `REPLACE`.
