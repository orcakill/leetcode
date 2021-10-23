138336错误提示：OpenSSL SSL_read: Connection was reset, errno 10054
        解决命令行：git config --global http.sslVerify "false"
                  git config --global --unset http.proxy
错误提示：warning: http.sslverify has multiple values
        解决命令：git config --global --replace-all  http.sslVerify "false"


更新项目，不考虑本地合并
解决命令行：

