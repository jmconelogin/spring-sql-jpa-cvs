Scenario: User makes call to API
Given The following endpoint
| method  | basepath | endpoint | content_type | path_param_key |
| GET | /something  | /api | application/JSOn | "String"|
Then For response cide and body I will assert