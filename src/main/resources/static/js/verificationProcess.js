
function verificationProcess (verifyCode, requestId, redirectPath){
    const xhttp = new XMLHttpRequest();
    xhttp.onload = function() {
        let json = JSON.parse(this.responseText)
        console.log(json);
        if(json['verified'] == "true") {
            window.location = redirectPath;
        }
    }
    xhttp.open("PUT", "/verification/verify/" + requestId, true);
    xhttp.setRequestHeader("Content-Type", "application/json");
    xhttp.send(JSON.stringify({code:verifyCode}));
}
