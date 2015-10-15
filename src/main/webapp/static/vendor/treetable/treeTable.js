
    var app = angular.module('treeTable', ['dataTransmitter']);
    app.factory('RecursionHelper', ['$compile', function ($compile) {
        return {
            /**
             * Manually compiles the element, fixing the recursion loop.
             * @param element
             * @param [link] A post-link function, or an object with function(s) registered via pre and post properties.
             * @returns An object containing the linking functions.
             */
            compile: function (element, link) {
                // Normalize the link parameter
                if (angular.isFunction(link)) {
                    link = { post: link };
                }

                // Break the recursion loop by removing the contents
                var contents = element.contents().remove();
                var compiledContents;
                return {
                    pre: (link && link.pre) ? link.pre : null,
                    /**
                     * Compiles and re-adds the contents
                     */
                    post: function (scope, element) {
                        // Compile the contents
                        if (!compiledContents) {
                            compiledContents = $compile(contents);
                        }
                        // Re-add the compiled contents to the element
                        compiledContents(scope, function (clone) {
                            element.append(clone);
                        });

                        // Call the post-linking function, if any
                        if (link && link.post) {
                            link.post.apply(null, arguments);
                        }
                    }
                };
            }
        };
    }]);
    app.directive('tree', ['provider', 'RecursionHelper', 'siteHelper', function (provider, RecursionHelper, siteHelper) {
        return {
            scope: {
                records: '=',
                model: '='
            },
            replace: true,
            templateUrl: 'Templates/Tree/tree.html',
            compile: function (element, b, c) {
                var link = function (scope, el, attr) {
                    scope.filter = {};
                    scope.properties = [];
                    scope.labels = [];
                    for (var x in scope.model) {
                        scope.filter[x] = '';
                        var property = {
                            property: x,
                            label: scope.model[x]
                        }
                        scope.properties.push(property);
                    }
                    scope.collapseId = siteHelper.guid();
                    scope.collapse = function (el) {
                        $(el).next().collapse('toggle');
                    }
                }
                return RecursionHelper.compile(element, link);
            }
        }
    }]);
    app.directive('treeCollapse', [function () {
        return {
            scope: false,
            replace: true,
            templateUrl: 'Templates/Tree/tree_collapse_button.html',
            link: function (scope, el) {
                scope.click = function () {
                    var next = $(el).parent().parent().next();
                    var coll = next.find('.collapse');
                    $(coll[0]).collapse('toggle');
                    if (next.hasClass('in')) {
                        $(el).find('span').remove();
                        var span = $('<span />')
                                    .addClass('glyphicon glyphicon-minus');
                        $(el).append(span);
                    } else {
                        $(el).find('span').remove();
                        var span = $('<span />')
                                    .addClass('glyphicon glyphicon-plus');
                        $(el).append(span);
                    }
                }
            }
        }
    }]);