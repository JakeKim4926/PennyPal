module.exports = {
    env: {
        browser: true,
        node: true,
    },
    parser: '@typescript-eslint/parser',
    plugins: ['@typescript-eslint', 'prettier'],
    extends: ['airbnb-typescript', 'airbnb'],

    parserOptions: {
        project: './tsconfig.json',
        sourceType: 'module',
        tsconfigRootDir: __dirname,
    },
    ignorePatterns: ['.eslintrc.js', 'webpack.config.js'],
    rules: {
        '@typescript-eslint/strict-boolean-expressions': 0,
        'react/react-in-jsx-scope': 'off',
    },
};
