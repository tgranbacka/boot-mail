angular.module('app').controller("MainController", function(){
    var vm = this;
    vm.title = 'Simple mail client';
    vm.subject = "Ange ärende";
    vm.address = "adress@doman.se";
    vm.attachment;
    vm.mailbody;
    vm.arendeid;
    vm.ahsarendeid;
    vm.personnummer;
    vm.kategori;
});