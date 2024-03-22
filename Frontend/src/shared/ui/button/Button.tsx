type ButtonProps = {
    child?: String;
    color?: String;
    size?: String;
    onClick?: React.MouseEventHandler<HTMLButtonElement>;
};

export function Button({ child, color, size = 'medium', onClick }: ButtonProps) {
    return (
        <button className={['Button', size, color].join(' ')} onClick={onClick}>
            {child}
        </button>
    );
}
