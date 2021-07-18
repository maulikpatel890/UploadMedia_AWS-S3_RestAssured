import java.io.File;

public class UploadMediaTest {
    public void uploadMedia(String username, String password, String contentType, String mediaName, String mediaUrlKey, String imageIdKey) throws IOException, ParseException {
        //This is the media file location.
        File mediaFile = new File(System.getProperty("user.dir")+"/src/test/resources/testResource/"+mediaName);
        //Below highlighted lines do the magic. You may get an error if you don’t set urlEncodingEnable to false and set config for CharSet.
        UploadMedia uploadMedia = new UploadMedia();

        RestAssured.urlEncodingEnabled = false;
        uploadMedia.callAWSAPIGateway(contentType);
        uploadMedia.get_media_url(mediaUrlKey,imageIdKey);
        URL mediaUrl = new URL(map.get(mediaUrlKey));
        RestAssured.given().config(RestAssured.config().encoderConfig(encoderConfig().appendDefaultContentCharsetToContentTypeIfUndefined(false)))
        .header("Content-Type", contentType)
        .body(mediaFile)
        .put(mediaUrl);
        RestAssured.urlEncodingEnabled = true;
        }
    
}