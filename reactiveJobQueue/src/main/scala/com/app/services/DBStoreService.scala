package com.app.services

import com.app.messages.User
import com.app.utils.{DBUtils, WranglerUtils}

/**
  * Created by rahulkumar on 06/03/17.
  */
object DBStoreService {
  def loadData(path: String): Boolean = {
   WranglerUtils.run(path)
  }
}
