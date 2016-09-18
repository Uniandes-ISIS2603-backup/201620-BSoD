/* 
 * Modulo Cliente.
 */
(function(ng)
{
    var mod = ng.module("clienteModule",["ngMessages", "ui.router"] );
    mod.constant("clienteContext", "api/clientes");
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) 
        {
            var basePath = 'src/modules/cliente/';
  
            $stateProvider.state('cliente', 
            {
                url: '/cliente',
                abstract :true,
                views: {
                        'mainView': 
                        {
                            controller: 'clienteCtrl',
                            controllerAs: 'clienteCtrl',
                            templateUrl: basePath + 'cliente.html'
                        }
                    }
            }).state('clienteList', {
                url: '/list',
                parent: 'cliente',
                views: {
                    'clienteView': {
                        controller: 'clienteCtrl',
                        controllerAs: 'clienteCtrl',
                        templateUrl: basePath + 'cliente.list.html'
                    }
                }
            }).state('clienteCreate', 
            {
                url: '/create',
                parent: 'cliente',
                views: 
                {
                    'clienteView': 
                    {
                        controller: 'clienteCtrl',
                        controllerAs: 'clienteCtrl',
                        templateUrl: basePath + 'cliente.create.html'
                    }
                }
            }).state('clienteEdit', 
            {
                url: '/{clienteId:int}/edit',
                parent: 'cliente',
                param: {clienteId: 'clienteId'},
                views: 
                {
                    'clienteView': 
                    {
                        controller: 'clienteCtrl',
                        controllerAs: 'clienteCtrl',
                        templateUrl: basePath + 'cliente.edit.html'
                    }
                }
            });
        }]);
    
}
)(window.angular);


