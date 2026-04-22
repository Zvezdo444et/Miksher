#define MyAppName "Miksher"
#define MyAppVersion "1.0"
#define MyAppPublisher "Zvezdo4et"
#define MyAppURL "https://github.com/Zvezdo444et"
#define MyAppExeName "Miksher.exe"

[Setup]
AppId={{90BB5E48-54D2-4A45-938E-89D53E35C5EC}
AppName={#MyAppName}
AppVersion={#MyAppVersion}
AppPublisher={#MyAppPublisher}
AppPublisherURL={#MyAppURL}
AppSupportURL={#MyAppURL}
AppUpdatesURL={#MyAppURL}
DefaultDirName={autopf}\{#MyAppName}
UninstallDisplayIcon={app}\{#MyAppExeName}
DisableProgramGroupPage=yes
AlwaysRestart=yes
UsePreviousAppDir=no
AlwaysShowDirOnReadyPage=yes
OutputDir=C:\Users\Zvezdo4et\Desktop
OutputBaseFilename=Miksher_Setup
SetupIconFile=C:\Users\Zvezdo4et\Downloads\download.ico
SolidCompression=yes
WizardStyle=modern

[Languages]
Name: "english"; MessagesFile: "compiler:Default.isl"
Name: "polish"; MessagesFile: "compiler:Languages\Polish.isl"
Name: "russian"; MessagesFile: "compiler:Languages\Russian.isl"
Name: "ukrainian"; MessagesFile: "compiler:Languages\Ukrainian.isl"

[Tasks]
Name: "desktopicon"; Description: "{cm:CreateDesktopIcon}"; GroupDescription: "{cm:AdditionalIcons}"; Flags: unchecked

[Files]
Source: "D:\Всячина\app\{#MyAppExeName}"; DestDir: "{app}"; Flags: ignoreversion
Source: "D:\Всячина\app\README.txt"; DestDir: "{app}"; Flags: ignoreversion
Source: "D:\Всячина\app\ПРОЧТИ_МЕНЯ.txt"; DestDir: "{app}"; Flags: ignoreversion
Source: "D:\Всячина\app\driver\*"; DestDir: "{app}\driver"; Flags: ignoreversion deleteafterinstall
Source: "D:\Всячина\app\jre-25-M\*"; DestDir: "C:\Program Files (x86)\Java\jre-25-M"; Flags: ignoreversion recursesubdirs createallsubdirs

[Icons]
Name: "{autoprograms}\{#MyAppName}"; Filename: "{app}\{#MyAppExeName}"
Name: "{autodesktop}\{#MyAppName}"; Filename: "{app}\{#MyAppExeName}"; Tasks: desktopicon

[Run]
Filename: "{app}\driver\VBCABLE_Setup_x64.exe"; Parameters: "/silent"; StatusMsg: "Установка драйвера VB-CABLE..."; Flags: runascurrentuser


