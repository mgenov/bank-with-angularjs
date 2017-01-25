var webpack = require('webpack')
var path = require('path')

var CopyWebpackPlugin = require('copy-webpack-plugin');
var ExtractTextPlugin = require("extract-text-webpack-plugin");

module.exports = {
  watch: true,
  context: __dirname,
  entry: {
    app: './index.js',
    vendor: [
      'jquery',
      'angular',
      'angular-route',
      'bootstrap'
    ]
  },
  output: {
    path: __dirname + '/build/assets/',
    filename: 'bank.bundle.js',
    publicPath: "./assets/"
  },
  module: {
    loaders: [
      {
        test: /\.js$/,
        exclude: /(node_modules|bower_components)/,
        loader: 'babel-loader',
        query: {
          presets: ['es2015']
        }
      },
      {
        test: /\.css$/,
        loader: ExtractTextPlugin.extract("style-loader", "css-loader?-url", {publicPath: '../'}),
        options: {relativeUrls: false}
      },
      {
        test: /\.html$/,
        loader: "ng-cache?prefix=[dir]&-url"
      },
      {
        test: /\.(jp?g|png|gif|svg)$/i,
        loader: 'file'
      },
      {
        test: /\.(woff|woff2)(\?v=\d+\.\d+\.\d+)?$/, loader: 'url?limit=10000&mimetype=application/font-woff'
      },
      {
        test: /\.ttf(\?v=\d+\.\d+\.\d+)?$/, loader: 'url?limit=10000&mimetype=application/octet-stream'
      },
      {
        test: /\.eot(\?v=\d+\.\d+\.\d+)?$/, loader: 'file'
      },
      {
        test: /\.svg(\?v=\d+\.\d+\.\d+)?$/, loader: 'url?limit=10000&mimetype=image/svg+xml'
      }
    ]
  },
  plugins: [
    new webpack.ProvidePlugin({
      $: "jquery",
      jQuery: "jquery"
    }),
    new webpack.optimize.CommonsChunkPlugin(/* chunkName= */"vendor", /* filename= */"vendor.bundle.js"),
    new ExtractTextPlugin("/styles/style.css"),
    new CopyWebpackPlugin([
      {from: './node_modules/bootstrap/dist/fonts', to: 'fonts'},
    ]),
  ],
  devServer: {
    hot: true,
    inline: true
  }
};
