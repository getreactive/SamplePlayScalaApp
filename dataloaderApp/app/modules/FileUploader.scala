package modules

import com.google.inject.AbstractModule
import net.codingwell.scalaguice.ScalaModule
import services.{UploadService, UploadServiceImpl}

/**
  * Created by rahulkumar on 04/03/17.
  */
class FileUploader extends AbstractModule with ScalaModule {
  def configure {
    bind[UploadService].to[UploadServiceImpl]
  }
}
