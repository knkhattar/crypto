Download cert file in pem format from browser or explore following command

echo quit\  | openssl s_client -servername self-signed.badssl.com -showcerts -connect self-signed.badssl.com:443\ | openssl x509 -outform PEM\ > badssl-com.pem

Pem to P12 conversion 

keytool -import -keystore badssl-com.p12 -alias badssl-com -file badssl-com.pem -trustcacerts

Enter keystore password: changeit  
Re-enter new password: changeit  
Trust this certificate? yes 