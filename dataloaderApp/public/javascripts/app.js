/**
 * Created by rahulkumar on 05/03/17.
 */
'use strict';
var version = {
    title : "v0"
};
var app = angular
    .module('dataloaderApp', [
        'ngAnimate',
        'ngAria',
        'ngCookies',
        'ngMessages',
        'ngResource',
        'ui.router',
        'ngMaterial',
        'ngFileUpload'
    ]).config(function($mdThemingProvider) {
        $mdThemingProvider.theme('dark-blue').backgroundPalette('blue').dark();
    });

app.config(function ($urlRouterProvider, $stateProvider) {

    $urlRouterProvider.otherwise('/');

    $stateProvider
        .state('home', {
            url: '/',
            templateUrl: '/app/views/home.html?v=' + version.title
        })
    ;
});