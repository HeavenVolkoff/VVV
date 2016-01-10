docReady(function(){
    function notEmpty(str){
        return str && str.length;
    }

    function serializeForm(form){
        var inputs = form.querySelectorAll("input");
        var obj = {};

        for(var counter = 0; counter < inputs.length; counter++){
            var elem = inputs[counter];
            obj[elem.name] = elem.value;
        }

        return obj;
    }

    var URL = {
        origin: window.location.href,
        login: "/rest/user/login"
    };

    var ID = {
        loginForm: "#login_form",
        loginButton: "#login_button",
        loginFormError: "#login_form_error"
    };

    var ELEMENTS = {};
    Object.keys(ID).forEach(function(id){
        ELEMENTS[id] = document.querySelector(ID[id]);
    });

    var DATA = {};
    (function(){
        var dataRegexp = /data-(\S+)/;
        var dataArray = document.querySelector("head").attributes;

        for(var counter = 0; counter < dataArray.length; counter++){
            var data = dataArray[counter];
            var dataName = dataRegexp.exec(data.name);

            if(dataName){
                DATA[dataName[1]] = data.value;
            }
        }

        console.log(DATA);
    })();

    var ajax = axios.create({
        baseURL: URL.origin,
        headers: {
            'X-Requested-By': DATA.csrf,
            'Content-Type': "application/json;charset=UTF-8"
        }
    });

    var FLAGS = {
        loggingIn: false
    };


    ELEMENTS.loginButton.addEventListener("click", function(){
        if(FLAGS.loggingIn){
            return;

        }else{
            FLAGS.loggingIn = true;
        }

        var formObj = serializeForm(ELEMENTS.loginForm);

        if(notEmpty(formObj.email) && notEmpty(formObj.password)){
            try{
                ajax.post(URL.login, JSON.stringify(formObj))
                    .then(function (response) {
                        ELEMENTS.loginFormError.style.display = "none";
                        console.log(response);
                    })
                    .catch(function (response) {
                        ELEMENTS.loginFormError.style.display = "";
                        ELEMENTS.loginFormError.innerText = response.data.data;
                        console.log(response)
                    })
                    .finally(function (){
                        FLAGS.loggingIn = false;
                    });

            }catch(error){
                console.log("ERROR Sending Login data: " + error);
            }
        }
    });
});