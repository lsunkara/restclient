package restclient

import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.context.annotation.Bean
import org.springframework.web.client.RestTemplate


@SpringBootApplication
class JsonConsumer {

   static void main(String[] args) {
        SpringApplication.run(JsonConsumer.class, args)
    }

    @Bean
    RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build()
    }

    @Bean
    CommandLineRunner run(RestTemplate restTemplate) throws Exception {
        {
            args ->
                List<Photo> photos
                if (args.length == 1) {
                    photos = restTemplate.getForObject("https://jsonplaceholder.typicode.com/photos?albumId={0}", Photo[], args[0])
                } else if (args.length == 0) {
                    photos = restTemplate.getForObject("https://jsonplaceholder.typicode.com/photos", Photo[])
                }
                if (photos.size() == 0)  println("******** No Photos found *******")
                photos.each {
                    println('[' + it.id + '] ' + it.title)
                }

        }
    }
}

