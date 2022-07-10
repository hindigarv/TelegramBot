# TelegramBot


## Set git user (at repository level)
```sh
git config user.name "hindigarv"
git config user.email "hindi.garv.001@gmail.com"
```


## Run in local

`TOKEN=123456:ABCD1234abcd_abcd1234ABCD ./gradlew run`

## Create a systemd service

1. Build the jar file: `./gradlew clean build`

2. Copy the jar file to HindiGarv directory

   1. For local: `cp ./build/libs/TelegramBot.jar ~/.hindigarv/hindigarv-telegram-bot.jar`
   2. For remote: `scp ./build/libs/TelegramBot.jar username@hostname:~/.hindigarv/hindigarv-telegram-bot.jar`

3. create a file `/etc/systemd/system/hindigarv-telegram-bot.service` with the following content
    ```service
    [Unit]
    Description=HindGarv Telegram Bot 
    After=network.target
    
    
    [Service]
    Environment="TOKEN=123456:ABCD1234abcd_abcd1234ABCD"
    ExecStart=java -jar /home/anil/.hindigarv/hindigarv-telegram-bot.jar
    Type=simple
    Restart=always
    
    [Install]
    WantedBy=default.target
    ```
4. Create aliases
    ```shell
    echo "alias hgtb-status='systemctl status hindigarv-telegram-bot.service'" >> ~/.bashrc
    echo "alias hgtb-start='systemctl start hindigarv-telegram-bot.service'" >> ~/.bashrc
    echo "alias hgtb-restart='systemctl restart hindigarv-telegram-bot.service'" >> ~/.bashrc
    echo "alias hgtb-stop='systemctl stop hindigarv-telegram-bot.service'" >> ~/.bashrc
    echo "alias hgtb-logs='journalctl -u hindigarv-telegram-bot.service -e -f'" >> ~/.bashrc
    ```
5. Check service status: `hgtb-status`
6. Done
    


