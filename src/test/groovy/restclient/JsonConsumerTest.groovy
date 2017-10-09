package restclient

import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpMethod
import org.springframework.http.MediaType
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.client.MockRestServiceServer
import org.springframework.web.client.RestTemplate

import static org.springframework.test.web.client.match.MockRestRequestMatchers.method
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess

@RunWith(SpringRunner)
@SpringBootTest
class JsonConsumerTest {

    @Autowired
    private RestTemplate restTemplate

    @Test
    void findPhotos() {

        MockRestServiceServer mockServer = MockRestServiceServer.createServer(restTemplate)

        mockServer.expect(requestTo("https://jsonplaceholder.typicode.com/photos")).andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess('[{"id": "1","title":"title1"},{"id": "2","title":"title2"}]', MediaType.APPLICATION_JSON));

        Photo[] photos = restTemplate.getForObject("https://jsonplaceholder.typicode.com/photos", Photo[])
        // Use the hotel instance...

        mockServer.verify()
        assert photos.size() == 2
        assert photos[0].title == "title1"
        assert photos[1].title == "title2"
    }

    @Test
    void findPhotosByAlbumId() {

        MockRestServiceServer mockServer = MockRestServiceServer.createServer(restTemplate)

        mockServer.expect(requestTo("https://jsonplaceholder.typicode.com/photos?albumId=3")).andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess('[{"id": "1","title":"byalbum"}]', MediaType.APPLICATION_JSON));

        Photo[] photos = restTemplate.getForObject("https://jsonplaceholder.typicode.com/photos?albumId={id}", Photo[],3)
        // Use the hotel instance...

        mockServer.verify()
        assert photos.size() == 1
        assert photos[0].title == "byalbum"
    }
}
