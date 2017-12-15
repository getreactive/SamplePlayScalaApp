package com.app.services

import java.io.{File, FileInputStream, IOException}

import com.typesafe.config.ConfigFactory
import org.apache.commons.net.ftp.{FTP, FTPClient}

/**
  * Created by rahulkumar on 06/03/17.
  */
object FileTransferService {

  def uploadFile(filePath:String, fileName: String): Unit = {

    val config = ConfigFactory.load()
    val port = config.getInt("ftp.port")
    val user = config.getString("ftp.user")
    val pass = config.getString("ftp.password")
    val server = config.getString("ftp.server")
    val ftpClient:FTPClient = new FTPClient
    try {
      ftpClient.connect(server, port)
      ftpClient.login(user, pass)
      ftpClient.enterLocalPassiveMode()
      ftpClient.setFileType(FTP.BINARY_FILE_TYPE)
      val firstLocalFile = new File(filePath)
      val firstRemoteFile = fileName
      val inputStream = new FileInputStream(firstLocalFile)
      val done = ftpClient.storeFile(firstRemoteFile, inputStream)
      inputStream.close()
      if (done) println("file uploaded successfully.")
    }
    catch {
      case e: IOException => {
        println("Error: " + e.getMessage)
        e.printStackTrace()
      }
    } finally {
      try {
        if (ftpClient.isConnected) {
          ftpClient.logout
          ftpClient.disconnect()
        }
      }
      catch {
        case e: IOException => {
          println("Error: " + e.getMessage)
          e.printStackTrace()
        }
      }
    }

  }
}
