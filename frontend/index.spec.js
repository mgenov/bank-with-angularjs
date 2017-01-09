import angular from 'angular'
import angularRoute from 'angular-route'
import mocks from 'angular-mocks/angular-mocks'

/*
 * The Global libraries used by the application as globals
 */
window.$ = window.jQuery = require('jquery')
window.angular = require('exports?window.angular!angular')

/**
 * `require` all modules in the given webpack context
 */
function requireAll(context) {
  context.keys().forEach(context);
}

/*
 * All sources need to be included till they start to use the ES6 modules.
 */
requireAll(require.context('./app', true, /^(?!.*(spec.js$|e2e.js|test.js)).*\.js$/));

/*
 * All test sources
 */
requireAll(require.context('./app', true, /\.spec.js$/));
