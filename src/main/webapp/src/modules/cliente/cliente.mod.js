(function (ng)
{
    var mod = ng.module("clienteModule", ["ngMessages", "ui.router"]);
    ////Aqui se dañoo 
    mod.constant("clienteContext", "api/clientes");
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider)
        {
            var basePath = 'src/modules/cliente/';
            $urlRouterProvider.otherwise("/clienteList")

            $stateProvider.state('cliente',
                    {
                        url: '/cliente',
                        abstract: true,
                        views: {
                            'mainView':
                                    {
                                        controller: 'clienteCtrl',
                                        controllerAs: 'clienteCtrl',
                                        templateUrl: basePath + 'cliente.html'
                                    }
                        }
                    }).state('clienteList',
                    {
                        url: '/list',
                        parent: 'cliente',
                        views: {
                            'clienteView':
                                    {
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
                    }).state('clienteVistaCliente',
                    {
                        url: '/{clienteId}/verCliente',
                        parent: 'cliente',
                        param: {clienteId: 'clienteId'},
                        views: {
                            'clienteView':
                                    {
                                        controller: 'clienteCtrl',
                                        controllerAs: 'clienteCtrl',
                                        templateUrl: basePath + 'cliente.verCliente.html'
                                    },
                            'childClienteView':
                                    {
                                        controller: 'clienteCtrl',
                                        controllerAs: 'clienteCtrl',
                                        templateUrl: basePath + 'cliente.verTarjetaPuntos.html'
                                    }
                        }
                    }).state('clienteRegistrarCompra',
                    {
                        url: '/{clienteId}/registrarComra',
                        parent: 'cliente',
                        param: {clienteId: 'clienteId'},
                        views: {
                            'clienteView':
                                    {
                                        controller: 'clienteCtrl',
                                        controllerAs: 'clienteCtrl',
                                        templateUrl: basePath + 'cliente.registrarCompra.html'
                                    }
                        }
                    }).state('clienteEdit',
                    {
                        url: '/{clienteId}/edit',
                        param: {clienteId: null},
                        parent: 'cliente',
                        views:
                                {
                                    'clienteView':
                                            {
                                                controller: 'clienteCtrl',
                                                controllerAs: 'clienteCtrl',
                                                templateUrl: basePath + 'cliente.edit.html'
                                            },
                                    'childClienteView':
                                            {
                                                templateUrl: basePath + 'cliente.instance.html'
                                            }
                                }
                    });
        }]);

}
)(window.angular);


