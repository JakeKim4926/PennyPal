type ButtonProps = {
    child?: String;
    color?: String;
    size?: String;
};

export function Button({ child, color, size = 'medium' }: ButtonProps) {
    return <button className={['Button', size, color].join(' ')}>{child}</button>;
}
