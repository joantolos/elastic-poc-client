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

## Data bulk



## Sense Chrome Plug-In



## Testing the Elastic API