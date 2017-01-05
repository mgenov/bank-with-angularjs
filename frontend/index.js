/**
 * `require` all modules in the given webpack context
 */
function requireAll(context) {
  context.keys().forEach(context);
}
// Collect all angular modules
requireAll(require.context(
        './app',
        /* use subdirectories: */ true,
        /^(?!.*(spec.js$|e2e.js)).*\.js$/
));

requireAll(require.context(
        './app/view',
        /* use subdirectories: */ true,
        /\.html$/
));