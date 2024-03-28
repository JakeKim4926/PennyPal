export function scrollTeamCreateArea(contentRef: React.RefObject<HTMLDivElement>, page: number) {
    contentRef.current?.scrollTo({
        top: contentRef.current?.offsetHeight * page,
        behavior: 'smooth',
    });
}
