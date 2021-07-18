public class UploadMedia {

    public Media callAWSAPIGateway(String contentType){
        String apiEndPoint = "https://<replace your domainName>/uploadImage";
        public Media buildMediaRequest(String contentType){
        Media media = Media.builder().build();
        media.setContentType(contentType);
        return media;
        }
        response = RestAssured.given().body(buildMediaRequest(contentType)).post(apiEndPoint);
        }
        //Get signed url from response and save it into map========
        public void get_media_url(String mediaUrlKey, String imageIdKey){
        Media media = response.as(Media.class);
        map.put(mediaUrlKey,media.getUploadURL());
        map.put(imageIdKey,media.getImageId());
        }
    
}