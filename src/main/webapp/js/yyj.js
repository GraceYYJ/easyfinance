  var _path = getRootPath();

  function getRootPath() {
      var strFullPath = window.document.location.href;
      var strPath = window.document.location.pathname;
      var pos = strFullPath.indexOf(strPath);
      var prePath = strFullPath.substring(0, pos);
      if (strPath.split('/').length == 3)
          return prePath;
      var postPath = strPath.substring(0, strPath.substr(1).indexOf('/') + 1);
      return (prePath + postPath);
  }
  
  function Login() {
      window.open(getRootPath() + "/admin/loginindex","_blank")
  }