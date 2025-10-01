UPDATE Category SET
    description = 'Lógica de programação, .NET, Automação e Produtividade',
    icon_path = '/assets/icons/programacao.svg'
WHERE code = 'programacao';

UPDATE Category SET
    description = 'HTML, CSS, Svelte, VueJS',
    icon_path = '/assets/icons/front-end.svg'
WHERE code = 'front-end';

UPDATE Category SET
    description = 'SQL e Banco de Dados, Engenharia de Dados, Análise de dados',
    icon_path = '/assets/icons/data-science.svg'
WHERE code = 'data-science';

INSERT INTO Category (name, code, color, `order`, description, icon_path) VALUES
('Inteligência Artificial', 'ia', '#6E4095', 4, 'IA para Criativos, IA para Programação, IA para Negócios', '/assets/icons/ia.svg'),
('DevOps', 'devops', '#F16165', 5, 'Linux, FinOps, Automação de processos', '/assets/icons/devops.svg'),
('UX & Design', 'ux-design', '#DC6EBE', 6, 'UI Design, Design System, UX Writing', '/assets/icons/ux.svg'),
('Mobile', 'mobile', '#FFBA05', 7, 'Flutter, Android, iOS', '/assets/icons/mobile.svg'),
('Inovação & Gestão', 'inovacao-gestao', '#FF8C2A', 8, 'Agilidade, Liderança, Ensino e Aprendizagem', '/assets/icons/inovacao.svg');