package restclient

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
class Photo {
    int albumId
    int id
    String description
    String title
    String url
    String thumbnailUrl

}
