package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.XRPDrivetrain;

public class TurnSeconds extends Command {
    private final XRPDrivetrain drivetrain;
    private final double speed;
    private final double seconds;

    private long startTime;

    public TurnSeconds(XRPDrivetrain drivetrain, double speed, double seconds) {
        this.drivetrain = drivetrain;
        this.speed = speed;
        this.seconds = seconds;

        addRequirements(drivetrain);
    }

    @Override
    public void initialize() {
        drivetrain.arcadeDrive(0.0, 0.0);
        startTime = System.currentTimeMillis();
    }

    @Override
    public void execute() {
        drivetrain.arcadeDrive(0.0, speed);
    }

    @Override
    public void end(boolean interrupted) {
        drivetrain.arcadeDrive(0.0, 0.0);
    }

    @Override
    public boolean isFinished() {
        long elapsed = System.currentTimeMillis() - startTime;
        return elapsed > seconds * 1000;
    }
}
