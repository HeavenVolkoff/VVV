<!DOCTYPE html>
<html lang="en">
    <head   data-csrf="${csrfKey}"
            <#if typeKey??>
                    data-typeKey="${typeKey}
            </#if>>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="csrf" >
        <title>Teste</title>

        <link rel="stylesheet" href="Resources/bower_components/material-design-lite/material.min.css">
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
        <link rel="stylesheet" href="Resources/Style/Home/style.css">

        <script src="Resources/bower_components/material-design-lite/material.min.js"></script>
        <script src="Resources/Script/Misc/documentReady.js"></script>
        <script src="Resources/bower_components/bluebird/js/browser/bluebird.min.js"></script>
        <script src="Resources/bower_components/axios/dist/axios.min.js"></script>
        <script src="Resources/Script/Home/script.js"></script>
    </head>

    <body>
        <div class="mdl-layout mdl-js-layout mdl-layout--fixed-header">
            <header class="mdl-layout__header">
                <div class="mdl-layout-icon"></div>
                <div class="mdl-layout__header-row">
                    <span class="mdl-layout__title">V.V.Viagens</span>
                </div>
                <div class="mdl-layout__tab-bar mdl-js-ripple-effect">
                    <a href="#home_tab" class="mdl-layout__tab<#if !(typeKey??)> is-active </#if>">Travels</a>
                    <a href="#login_tab" class="mdl-layout__tab">Log In</a>
                    <a href="#sign_up_tab" class="mdl-layout__tab<#if typeKey??> is-active </#if>">Sign Up</a>
                </div>
            </header>
            <main class="mdl-layout__content">
                <section class="mdl-layout__tab-panel<#if !(typeKey??)> is-active </#if>" id="home_tab">
                    <div class="page-content">
                        Cards
                    </div>
                </section>
                <section class="mdl-layout__tab-panel" id="login_tab">
                    <div class="page-content">
                        <div class="mdl-grid">
                            <div class="mdl-layout-spacer"></div>
                            <div class="mdl-cell mdl-cell--6-col mdl-cell--6-col-tablet mdl-cell--10-col-phone mdl-cell--middle">
                                <div class="mdl-card mdl-shadow--6dp" style="width: 100%">
                                    <div class="mdl-card__title mdl-color--primary mdl-color-text--white">
                                        <h2 class="mdl-card__title-text">User:</h2>
                                    </div>
                                    <div class="mdl-card__supporting-text" style="width: initial;">
                                        <div id="login_form_error" class="mdl-grid--no-spacing mdl-color--red-500 mdl-color-text--white mdl-typography--text-center" style="display: none; padding: 5px"></div>
                                        <form action="javascript:void()" id="login_form">
                                            <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label" style="width: 100%">
                                                <input class="mdl-textfield__input" type="text" name="email"/>
                                                <label class="mdl-textfield__label" for="email">Email</label>
                                            </div>
                                            <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label" style="width: 100%">
                                                <input class="mdl-textfield__input" type="password" name="password"/>
                                                <label class="mdl-textfield__label" for="password">Password</label>
                                            </div>
                                        </form>

                                    </div>
                                    <div class="mdl-card__actions mdl-card--border">
                                        <button class="mdl-button mdl-button--colored mdl-js-button mdl-js-ripple-effect" id="login_button">Log in</button>
                                    </div>
                                </div>
                            </div>
                            <div class="mdl-layout-spacer"></div>
                        </div>
                    </div>
                </section>
                <section class="mdl-layout__tab-panel<#if typeKey??> is-active </#if>" id="sign_up_tab">
                    <div class="page-content">
                        SignUp
                    </div>
                </section>
            </main>
        </div>
    </body>
</html>