// const path = require("path");
// const HtmlWebpackPlugin = require("html-webpack-plugin");

// module.exports = () => {
//     const isDevMod = process.env.NODE_ENV === "dev";

//     return {
//         entry: "./src/index.js",
//         output: {
//             filename: "bundle.js",
//             path: path.resolve(__dirname, "dist"),
//             clean: true,
//         },
//         resolve: {
//             extensions: [".ts", ".tsx", ".js", ".jsx"],
//             alias: {
//                 // 절대경로 설정
//             },
//         },
//         devServer: {
//             port: 3000,
//             hot: true,
//         },
//         devtool: isDevMod ? "eval-source-map" : "source-map",
//         module: {
//             rules: [
//                 // js loader
//                 {
//                     test: /\.(js|jsx)$/,
//                     exclude: /node_modules/,
//                     use: {
//                         loader: "babel-loader",
//                         options: {
//                             presets: [
//                                 "@babel/preset-env",
//                                 [
//                                     "@babel/preset-react",
//                                     { runtime: "automatic" },
//                                 ],
//                             ],
//                         },
//                     },
//                 },

//                 // css loader
//                 {
//                     test: /\\.css$/,
//                     use: ["style-loader", "css-loader"],
//                 },

//                 // asset loader
//                 {
//                     test: /\\.(png|svg|jpg|jpeg|gif)$/i,
//                     type: "asset/resource",
//                 },
//             ],
//         },
//         plugins: [
//             new HtmlWebpackPlugin({
//                 template: "public/index.html",
//             }),
//         ],
//     };
// };
