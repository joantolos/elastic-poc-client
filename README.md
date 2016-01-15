# Elastic POC Client

Client for testing the Elastic Server hosted on Facetflow https://facetflow.com/
Facetflow offers the possibility to create a simple Elastic Server for free with some limitations:

* Only 5000 docs
* 500 MB space

Also, it's a little slow but again, it's free. So, the URI for elastic is: 

* kJ0lZOgk80yD78Czt217DgDw5BOg7GES:@elasticsandbox.east-us.azr.facetflow.io

You are welcome to play with it (just don't break it).

## Creating the index / mappings

You can create the index at the same time with the mappings. The mappings helps to define an structure where the documents will live. In our example, this data structure matches the following mappings JSON:

![alt text](planet.png "Planet data structure")

This is how you do the mappings for this structure. Covers lists and nested elements:

    {
      "settings": {
        "analysis": {
          "analyzer": {
            "explode_names": {
              "tokenizer": "whitespace",
              "filter": [
                "lowercase",
                "explode_names_filter"
              ]
            }
          },
          "filter": {
            "explode_names_filter": {
              "type": "word_delimiter",
              "preserve_original": "true",
              "catenate_words": "true"
            }
          }
        },
        "number_of_replicas": 0
      },
      "mappings": {
        "planets": {
          "properties": {
            "planet": {
              "properties": {
                "id": {
                  "type": "long"
                },
                "name": {
                  "type": "string"
                },
                "distanceFromSun": {
                  "type": "long"
                },
                "details": {
                  "properties": {
                    "volume": {
                      "properties": {
                        "unit": {
                          "type": "string"
                        },
                        "value": {
                          "type": "double"
                        }
                      }
                    },
                    "mass": {
                      "properties": {
                        "unit": {
                          "type": "string"
                        },
                        "value": {
                          "type": "double"
                        }
                      }
                    },
                    "density": {
                      "properties": {
                        "unit": {
                          "type": "string"
                        },
                        "value": {
                          "type": "double"
                        }
                      }
                    }
                  }
                }
              }
            }
          }
        }
      }
    }

Using the Elastic API, we can make this POST call:

PUT /planets

With the body before.

## Data bulk

Then you want to insert or bulk some data into your structure, here the example for two planets:

    POST /_bulk
    {"index":{"_index":"planets","_type":"planet","_id":"1"}}
    {"planet":[{"id":"1","name":"Mercury","distanceFromSun":"57909175","details":{"volume":{"unit":"km^3","value":"6.083e10"},"mass":{"unit":"km^3","value":"1"},"density":{"unit":"g/cm^3","value":"4"}}}]}
    {"index":{"_index":"planets","_type":"planet","_id":"2"}}
    {"planet":[{"id":"2","name":"Venus","distanceFromSun":"108208930","details":{"volume":{"unit":"km^3","value":"9.28e11"},"mass":{"unit":"km^3","value":"2"},"density":{"unit":"g/cm^3","value":"5"}}}]}

## Searching the data

Then, having two planets on Elastic, you can perform a query:

Using GET call:

    GET /planets/_search?q=planet.id=2

Or using POST call:

    POST /planets/_search
    { "query" : { "term" : { "planet.id" : "2" } } }


## Sense Chrome Plug-In

There is a very useful plugin for chrome where you can interact with an Elastic server in a very comfortable way:

https://chrome.google.com/webstore/detail/sense-beta/lhjgkmllcaadmopgmanpapmpjgmfcfig

You only have to set the server URI to: kJ0lZOgk80yD78Czt217DgDw5BOg7GES:@elasticsandbox.east-us.azr.facetflow.io

And here you have some different calls to make some operations with the content:

    DELETE /planets
    
    PUT /planets
    {
      "settings": {
        "analysis": {
          "analyzer": {
            "explode_names": {
              "tokenizer": "whitespace",
              "filter": [
                "lowercase",
                "explode_names_filter"
              ]
            }
          },
          "filter": {
            "explode_names_filter": {
              "type": "word_delimiter",
              "preserve_original": "true",
              "catenate_words": "true"
            }
          }
        },
        "number_of_replicas": 0
      },
      "mappings": {
        "planets": {
          "properties": {
            "planet": {
              "properties": {
                "id": {
                  "type": "long"
                },
                "name": {
                  "type": "string"
                },
                "distanceFromSun": {
                  "type": "double"
                },
                "details": {
                  "properties": {
                    "volume": {
                      "properties": {
                        "unit": {
                          "type": "string"
                        },
                        "value": {
                          "type": "double"
                        }
                      }
                    },
                    "mass": {
                      "properties": {
                        "unit": {
                          "type": "string"
                        },
                        "value": {
                          "type": "double"
                        }
                      }
                    },
                    "density": {
                      "properties": {
                        "unit": {
                          "type": "string"
                        },
                        "value": {
                          "type": "double"
                        }
                      }
                    }
                  }
                }
              }
            }
          }
        }
      }
    }
    
    POST /_bulk
    {"index":{"_index":"planets","_type":"planet","_id":"1"}}
    {"planet":[{"id":"1","name":"Mercury","distanceFromSun":"57909175","details":{"volume":{"unit":"km^3","value":"6.083e10"},"mass":{"unit":"km^3","value":"1"},"density":{"unit":"g/cm^3","value":"4"}}}]}
    {"index":{"_index":"planets","_type":"planet","_id":"2"}}
    {"planet":[{"id":"2","name":"Venus","distanceFromSun":"108208930","details":{"volume":{"unit":"km^3","value":"9.28e11"},"mass":{"unit":"km^3","value":"2"},"density":{"unit":"g/cm^3","value":"5"}}}]}
    
    GET /planets
    
    GET /planets/_search?q=planet.id=1
    
    POST /planets/_search
    { "query" : { "term" : { "planet.id" : "8" } } }
    
    POST /planets/_search
    {
        "sort" : [{ "planet.id" : { 
                        "order" : "desc"
                    }
                }
        ],
        "size" : 1
    }
    
    DELETE /planets/_query
    { "query" : { "term" : { "planet.id" : "8" } } }
    
## Testing the Elastic API
 
If you execute the main class on the project: **com.joantolos.poc.elastic.ElasticPocClient.java**

you will see some examples. Basically what it does is:

1. Insert Mercury, Venus and Earth
2. Search for planet with id 3 (which is Earth)
3. Search for planet with name Venus
4. Search for the planet farther from the Sun (At this point shoud be Earth)
5. Insert the rest of the planets
6. Search for the planet farther from the Sun (At this point should be Neptune)
7. Delete planet Neptune
8. Search for the planet farther from the Sun (At this point should be Uranus)

## Take aways

The interesting part of the POC are the classes:

1. **com.joantolos.poc.elastic.engine.remote.ElasticServer.java** Which uses Feign client to make the calls to the Elastic server
2. **com.joantolos.poc.elastic.engine.ElasticSearchEngine** Which acts as a proxy to Elastic Server